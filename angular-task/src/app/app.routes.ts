import { Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { ProductCardComponent } from './components/product-card/product-card.component';
import { LogInComponent } from './components/log-in/log-in.component';
import { HomeComponent } from './components/home/home.component';

export const routes: Routes = [
    {
        path : 'home', component: HomeComponent
    },
    {
        path : 'login', component: LogInComponent
    },
    {
        path : "**", redirectTo : 'home'
    }
];
