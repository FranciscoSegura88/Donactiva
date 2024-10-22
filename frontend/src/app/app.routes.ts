import { Routes } from '@angular/router';
import { MainComponent } from './pages/main/main.component';
import { LoginComponent } from './components/login/login.component';
import { SignUpComponent } from './components/sign-up/sign-up.component';

export const routes: Routes = [
    {path: 'main', component: MainComponent},
    {path: 'login', component: LoginComponent},
    {path: 'sign-up', component: SignUpComponent}
];
