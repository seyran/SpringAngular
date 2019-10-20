import {Component} from 'angular2/core';
import {RouteConfig, ROUTER_DIRECTIVES, ROUTER_PROVIDERS} from 'angular2/router';
import {HeroesComponent } from './heroes.component.ts';
import {DashboardComponent } from './dashboard.component.ts';
import {HeroDetailComponent } from './hero-detail.component.ts';

import {LoggerService} from './../services/logger.service.ts';
import {HeroService} from './../services/hero.service.ts';

@Component({
    selector: 'app',
    template: `
    <h1>{{title}}</h1>
    <nav>
        <a [routerLink]="['Dashboard']">Dashboard</a>
        <a [routerLink]="['Heroes']">Heroes</a>
    </nav>
    <router-outlet></router-outlet>
  `,
    styleUrls:  ['app/styles/app.component.css'],

    directives: [
        ROUTER_DIRECTIVES
    ],
    providers:  [
        ROUTER_PROVIDERS,
        LoggerService,
        HeroService
    ],
})

@RouteConfig([
    {
        path: '/dashboard',
        name: 'Dashboard',
        component: DashboardComponent,
        useAsDefault: true
    },
    {
        path: '/heroes',
        name: 'Heroes',
        component: HeroesComponent
    },
    {
        path: '/detail/:id',
        name: 'HeroDetail',
        component: HeroDetailComponent
    }
])

export class AppComponent {
    title = 'Tour of Heroes';
}
