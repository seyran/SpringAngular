import {Component} from 'angular2/core';
import {OnInit} from 'angular2/core';
import {Router } from 'angular2/router';

import {LoggerService} from './../services/logger.service.ts';
import {HeroService} from './../services/hero.service.ts';

import {Hero} from './../models/hero';

@Component({
    selector: 'my-heroes',
    templateUrl: 'app/templates/heroes.component.html',
    styleUrls:  ['app/styles/heroes.component.css']
})

//  <my-hero-detail [hero]="selectedHero"></my-hero-detail>

export class HeroesComponent implements OnInit {
    heroes: Hero[];
    selectedHero: Hero;

    constructor(
        private _router: Router,
        private _loggerService: LoggerService,
        private _heroService: HeroService
    ) {
    }

    ngOnInit() {
        this.getHeroes();
    }

    onSelect(hero: Hero) {
        this.selectedHero = hero;
    }

    getHeroes() {
        this._heroService.getHeroes().then(heroes => this.heroes = heroes);
        this._loggerService.log("getHeroes");
    }

    gotoDetail() {
        this._router.navigate(['HeroDetail', { id: this.selectedHero.id }]);
    }
}

