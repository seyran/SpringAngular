import {Injectable} from 'angular2/core';

@Injectable()
export class LoggerService {

    log(message:String) {
        console.log(message);
        //console.error.bind(console);
    }
}