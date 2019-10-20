const BATH_PATH:String = "./../";

import {HEROES} from './../models/mock-heroes';
import {Hero} from './../models/hero';
import {Injectable} from 'angular2/core';

@Injectable()
export class HeroService {
    getHeroes():Promise<Array<Hero>> {
        return Promise.resolve(HEROES);
        //return HEROES;
    }

    getHeroesSlowly():Promise<Array<Hero>> {
        return new Promise<Hero[]>(resolve =>
            setTimeout(()=>resolve(HEROES), 2000) // 2 seconds
        );
    }

    getHero(id: number) :Promise<Hero> {
        return Promise.resolve(HEROES).then(
            heroes => heroes.filter(hero => hero.id === id)[0]
        );
    }
}