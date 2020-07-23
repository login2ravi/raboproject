import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { UsersService } from './users.service';


@Injectable({
  providedIn: 'root'
})
export class GlobalDataService {

  private dataSource = null;

  constructor() { }

  updatedDataSelection(data: any): void{
    this.dataSource.next(data);
  }

  getDataSelection(){
    if (this.dataSource == null){
      //this.dataSource = new BehaviorSubject<UsersService>(new UsersService());
    }
    return this.dataSource.asObservable();
  }
}
