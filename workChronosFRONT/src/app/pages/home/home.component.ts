import { Component, OnInit } from '@angular/core';
import { DateTime } from 'luxon';
import { AttendanceRecordGateway, AttendanceRecordOutput } from '../../api/services/attendance-record-gateway';
import { areSameDay } from '../../shared/utils/areSameDay';
import { SidebarComponent } from '../../components/sidebar/sidebar.component';
import { NavbarComponent } from "../../components/navbar/navbar.component";
import { generateGreeting } from '../../shared/utils/generateGreeting';
import { UserGateway, UserOutput } from '../../api/services/user-gateway';
import { forkJoin } from 'rxjs';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  standalone: true,
  imports: [SidebarComponent, NavbarComponent]
})
export class HomeComponent implements OnInit {
  
  private userID = '2bb488d4-4d76-4503-8662-796cc3521d35';
  user?: UserOutput;
  latestRecord?: AttendanceRecordOutput;

  greeting = generateGreeting();

  constructor(
    private attendanceRecordGateway: AttendanceRecordGateway,
    private userGateway: UserGateway
  ) {}

  ngOnInit() {
    forkJoin({
      user: this.userGateway.getById(this.userID),
      attendanceRecord: this.attendanceRecordGateway.getlatestByUserID(this.userID)
    }).subscribe({
      next: ({ user, attendanceRecord }) => {
        this.user = user;

        console.log(attendanceRecord.workDate);
        console.log(DateTime.now().toISO());
        console.log(areSameDay(attendanceRecord?.entrie.workStart, DateTime.now().toISO()));

        if (true){
          this.latestRecord = attendanceRecord;
        }
        
      },
      error: (error) => {
        console.error('Erro ao carregar os dados:', error);
      }
    });
  } 
}
