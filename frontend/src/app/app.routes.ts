import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { SignUpComponent } from './components/sign-up/sign-up.component';
import { ForgotPasswordComponent } from './components/forgot-password/forgot-password.component';
import { TutorialComponent } from './components/tutorial/tutorial.component';

export const routes: Routes = [
    {path: 'login', component: LoginComponent},
    {path: 'sign-up', component: SignUpComponent},
    {path: 'forgot-password', component : ForgotPasswordComponent},
    {path: 'tutorial', component : TutorialComponent}
];
