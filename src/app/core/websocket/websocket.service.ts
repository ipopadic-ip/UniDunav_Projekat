import { Injectable } from '@angular/core';
import { Client, IMessage, Stomp } from '@stomp/stompjs';
import SockJS from 'sockjs-client';
import { AuthService } from '../services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class WebsocketService {
  private stompClient: Client | null = null;
  private connected = false;
  private pendingSubs: { dest: string, cb: (msg:IMessage) => void }[] = [];

  constructor(private authService: AuthService) {}

  connect(): void {
    if (this.connected) return;

    const token = this.authService.getToken();

    this.stompClient = new Client({
      webSocketFactory: () => new SockJS('http://localhost:8080/ws'),
      connectHeaders: { Authorization: `Bearer ${token}` },
      debug: str => console.log(str),
      onConnect: () => {
        console.log('Connected to WebSocket');
        this.connected = true;

        // aktiviraj sve pending subscribes
        this.pendingSubs.forEach(s => this.stompClient?.subscribe(s.dest, s.cb));
        this.pendingSubs = [];
      },
      onStompError: frame => {
        console.error('Broker error:', frame.headers['message']);
      }
    });
    this.stompClient.activate();
  }

  subscribe(dest: string, cb: (msg:IMessage) => void): void {
    if (this.connected) {
      this.stompClient?.subscribe(dest, cb);
    } else {
      this.pendingSubs.push({ dest, cb });
    }
  }

  subscribeToEdit(verzijaId: number, callback: (msg: IMessage) => void): void {
    this.subscribe(`/topic/edit/${verzijaId}`, callback);
  }

  subscribeToTyping(verzijaId: number, callback: (msg: IMessage) => void): void {
    this.subscribe(`/topic/typing/${verzijaId}`, callback);
  }

  send(destination: string, body: any): void {
    if (this.connected) {
      this.stompClient?.publish({ destination, body: JSON.stringify(body) });
    } else {
      console.warn('Not connected yet. Message not sent.');
    }
  }
  sendEditMessage(msg: any) { this.send('/app/edit', msg); }
  sendTypingStatus(msg: any) { this.send('/app/typing', msg); }
  
  disconnect(): void {
    if (this.stompClient && this.connected) {
      this.stompClient.deactivate();
      this.connected = false;
    }
  }
}

