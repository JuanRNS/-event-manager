import { HttpClient } from '@angular/common/http';
import { Injectable, NgZone } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from '../../../environments/environment';
import { ToastService } from '../../core/services/toast.service';

declare const google: any;

declare global {
  interface Window {
    handleCredentialResponse: (response: any) => void;
  }
}

@Injectable({
  providedIn: 'root',
})
export class AuthGoogle {
  constructor(
    private readonly _http: HttpClient, 
    private readonly _router: Router, 
    private readonly _zone: NgZone,
    private readonly _toast: ToastService
  ) {
    window.handleCredentialResponse = this.handleCredentialResponse.bind(this);
  }

  public initializeGoogleSignIn(containerId: string) {
    if (typeof google !== 'undefined') {
      google.accounts.id.initialize({
        client_id: environment.googleClientId,
        callback: this.handleCredentialResponse.bind(this)
      });

      google.accounts.id.renderButton(
        document.getElementById(containerId),
        { theme: "outline", size: "large" }
      );
    }
  }

  private handleCredentialResponse(response: any) {
    this._zone.run(() => {
      const idToken = response.credential;
      this.sendTokenToBackend(idToken);
    });
  }

  private sendTokenToBackend(idToken: string) {
    this._http.post<string>(`${environment.api}auth/google`, { token: idToken }, { responseType: 'text' as 'json' })
      .subscribe({
        next: (myJwt) => {
          localStorage.setItem('token', myJwt);
          this._router.navigate(['dashboard']);
        },
        error: (err) => {
          this._toast.error('Falha na autenticação Google:', err);
        }
      });
  }
}
