import { Component, OnInit } from '@angular/core';
import { DateTime, Duration, DurationLike } from 'luxon';
import { AttendanceRecordGateway, AttendanceRecordOutput } from '../../api/services/attendance-record-gateway';
import { areSameDay } from '../../shared/utils/areSameDay';
import { SidebarComponent } from '../../components/sidebar/sidebar.component';
import { NavbarComponent } from "../../components/navbar/navbar.component";
import { generateGreeting } from '../../shared/utils/generateGreeting';
import { UserGateway, UserOutput } from '../../api/services/user-gateway';
import { forkJoin } from 'rxjs';
import { AuthGateway, LoginOutput } from '../../api/services/auth-gateway';

type Action = 'start' | 'end' | 'startBreak' | 'endBreak';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  standalone: true,
  imports: [SidebarComponent, NavbarComponent]
})
export class HomeComponent implements OnInit {
  
  user: LoginOutput | null;
  private _latestRecord?: AttendanceRecordOutput;
  availableActions: Action[] = [];
  greeting = generateGreeting();
  remainingHoursToWork = Duration.fromObject({ hours: 8 });

  constructor(
    private attendanceRecordGateway: AttendanceRecordGateway,
    private userGateway: UserGateway
  ) {
    this.user = AuthGateway.getUserSession();
  }

  get latestRecord() {
    return this._latestRecord;
  }

  set latestRecord(record: AttendanceRecordOutput | undefined) {
    if (!record) return;
    this._latestRecord = record;
    this.remainingHoursToWork = Duration.fromObject({ hours: 8 }).minus(record.workDuration as DurationLike);
  }

  start() {
    if (!this.user) return;
    this.attendanceRecordGateway.startDay(this.user?.id).subscribe({
      next: (record) => {
        this.latestRecord = record;
        this.availableActions = ['startBreak', 'end'];
      },
      error: (error) => {
        console.error('Erro ao iniciar o registro de ponto:', error);
      }
    });
  }

  startBreak() {
    if (!this.user) return;
    this.attendanceRecordGateway.startBreak(this.user?.id).subscribe({
      next: (record) => {
        this.latestRecord = record;
        this.availableActions = ['endBreak', 'end'];
      },
      error: (error) => {
        console.error('Erro ao iniciar o intervalo:', error);
      }
    });
  }

  finishBreak() {
    if (!this.user) return;
    this.attendanceRecordGateway.finishBreak(this.user?.id).subscribe({
      next: (record) => {
        this.latestRecord = record;
        this.availableActions = ['startBreak', 'end'];
      },
      error: (error) => {
        console.error('Erro ao finalizar o intervalo:', error);
      }
    });
  }

  finish() {
    if (!this.user) return;
    this.attendanceRecordGateway.finishDay(this.user?.id).subscribe({
      next: (record) => {
        this.latestRecord = record;
        this.availableActions = [];
      },
      error: (error) => {
        console.error('Erro ao finalizar o registro de ponto:', error);
      }
    });
  }

  startAnotherDay() {
    this.start();
    this.availableActions = ['start'];
  }
  
  ngOnInit() {
    if (!this.user) return;
    forkJoin({
      user: this.userGateway.getById(this.user?.id),
      attendanceRecord: this.attendanceRecordGateway.getlatestByUserID(this.user.id)
    }).subscribe({
      next: ({ user, attendanceRecord }) => {
        const today = DateTime.now().day;
        const latestRecordIsToday = DateTime.fromFormat(attendanceRecord.entrie.workStart, 'HH:mm dd/MM/yyyy').day === today;
        
        if (latestRecordIsToday) this.latestRecord = attendanceRecord;
        
        if (!this.latestRecord) this.availableActions.push('start');
        if (!latestRecordIsToday) this.availableActions.push('start');
        if (latestRecordIsToday && !attendanceRecord.entrie.workEnd) this.availableActions.push('end');
        

        const hasBreakNotFinished = attendanceRecord.entrie.breaks?.some(breakItem => !breakItem.end);
        if (latestRecordIsToday && !hasBreakNotFinished && !this.latestRecord?.entrie.workEnd) this.availableActions.push('startBreak');
        if (latestRecordIsToday && hasBreakNotFinished) this.availableActions.push('endBreak');

      },
      error: (error) => {
        this.availableActions = ['start'];
      }
    });
  } 
}
