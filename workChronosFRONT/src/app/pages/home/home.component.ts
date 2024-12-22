import { Component, OnInit } from '@angular/core';
import { DateTime } from 'luxon'; // Importe o DateTime do Luxon
import { AttendanceRecordGateway, AttendanceRecordOutput } from '../../api/services/attendance-record-gateway';
import { areSameDay } from '../../shared/utils/areSameDay';
import { SidebarComponent } from '../../components/sidebar/sidebar.component';
import { NavbarComponent } from "../../components/navbar/navbar.component";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  standalone: true,
  imports: [SidebarComponent, NavbarComponent]
})
export class HomeComponent implements OnInit {
  user = {
    name: 'John Doe',
    email: ''
  };

  _latestRecord?: AttendanceRecordOutput;
  latestRecordIsToday: boolean = false;

  constructor(private attendanceRecordGateway: AttendanceRecordGateway) {}

  ngOnInit() {
    this.loadAttendanceRecords('2bb488d4-4d76-4503-8662-796cc3521d35');
  }

  set latestRecord(value: AttendanceRecordOutput | undefined) {
    this._latestRecord = value;
    this.latestRecordIsToday = areSameDay(value?.entrie.workStart, DateTime.now().toISO());
  }

  get latestRecord(): AttendanceRecordOutput | undefined {
    return this._latestRecord;
  }

  loadAttendanceRecords(userId: string) {
    this.attendanceRecordGateway.getlatestByUserID(userId).subscribe({
      next: (records) => {
        this.latestRecord = records;
        console.log('Registros de presença carregados com sucesso', records);
      },
      error: (error) => {
        console.error('Erro ao carregar os registros de presença', error);
      }
    });
  }
}
