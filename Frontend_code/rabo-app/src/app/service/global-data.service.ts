import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { UsersService } from './users.service';


@Injectable({
  providedIn: 'root'
})
export class GlobalDataService {

  private dataSource = new BehaviorSubject<UsersService>(new UsersService());

  constructor() { }

  updatedDataSelection(data: any): void{
    this.dataSource.next(data);
  }

  getDataSelection(){
    return this.dataSource.asObservable();
  }
}
