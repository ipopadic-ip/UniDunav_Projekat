// src/app/core/interceptors/auth.interceptor.ts
import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { HttpErrorResponse } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const authService = inject(AuthService);
  const authToken = authService.getToken();

  let authReq = req;
  if (authToken) {
    authReq = req.clone({
      setHeaders: {
        Authorization: `Bearer ${authToken}`,
      },
    });
  }

  return next(authReq).pipe(
    catchError(err => {
      if (err.status === 401 || err.status === 403) {
        // Token expired or unauthorized
        authService.logout();
      }
      return throwError(() => err);
    })

  // return next(authReq).pipe(
  //   catchError((error: HttpErrorResponse) => {
  //     if (error.status === 401 || error.status === 403) {
  //       authService.logout(); // ako je token istekao ili nije validan, logoutuj
  //     }
  //     return throwError(() => error);
  //   })
  );
};

// export const authInterceptor: HttpInterceptorFn = (req, next) => {
//   const authService = inject(AuthService);
//   const authToken = authService.getToken();

//   if (authToken) {
//     const authReq = req.clone({
//       setHeaders: {
//         Authorization: `Bearer ${authToken}`,
//       },
//     });
//     return next(authReq);
//   }

//   return next(req);
// };
