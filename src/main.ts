/// <reference types="@angular/localize" />

// import { bootstrapApplication } from '@angular/platform-browser';
// import { appConfig } from './app/app.config';
// import { AppComponent } from './app/app.component';

// bootstrapApplication(AppComponent, appConfig)
//   .catch((err) => console.error(err));

import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent } from './app/app.component';
import { provideRouter } from '@angular/router';
import { routes } from './app/app.routes';
import { provideAnimations } from '@angular/platform-browser/animations';
import { provideHttpClient, withFetch  } from '@angular/common/http';
import { appConfig } from './app/app.config';


bootstrapApplication(AppComponent, {
  providers: [
    provideRouter(routes), // 🚀 Routing bez AppModule
    provideAnimations(), // 🚀 Angular Animations
    provideHttpClient(withFetch()),
    ...appConfig.providers
    
  ]
}).catch(err => console.error(err));


