import { Component, OnInit, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';
import { TableModule } from 'primeng/table';
import { DropdownModule } from 'primeng/dropdown';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';
import { ToastModule } from 'primeng/toast';
import { Table } from 'primeng/table';
import { Api, Complaint, Team } from '../../../../core/services/api';
import { WebSocket } from '../../../../core/services/websocket';
import { MessageService } from 'primeng/api';
import { StatusPriorityPipe } from '../../../../shared/pipes/status-pipe';
@Component({
  selector: 'app-complaint-dashboard',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    TableModule,
    DropdownModule,
    ButtonModule,
    CardModule,
    ToastModule,
    StatusPriorityPipe
  ],
  templateUrl: './complaint-dashboard.component.html'
})
export class ComplaintDashboardComponent implements OnInit {
  @ViewChild('dt') table!: Table;

  complaints: Complaint[] = [];
  teams: Team[] = [];
  selectedTeam: Team | null = null;

  constructor(
    private api: Api,
    private ws: WebSocket,
    private messageService: MessageService
  ) {}

  ngOnInit(): void {
    this.loadData();
    this.setupRealTimeUpdates();
  }

  loadData() {
    this.api.getComplaints().subscribe(data => this.complaints = data);
    this.api.getTeams().subscribe(data => this.teams = data);
  }

  setupRealTimeUpdates() {
    this.ws.complaintUpdates.subscribe(updated => {
      const index = this.complaints.findIndex(c => c.id === updated.id);
      if (index > -1) {
        this.complaints[index] = updated;
      } else {
        this.complaints.unshift(updated);
      }
      this.table.reset();
      this.messageService.add({
        severity: 'info',
        summary: 'Live Update',
        detail: `Complaint ${updated.ticketId} status changed`
      });
    });

    this.ws.slaBreachAlerts.subscribe(() => {
      this.messageService.add({
        severity: 'warn',
        summary: 'SLA Breach Alert',
        detail: 'One or more complaints have breached SLA deadline'
      });
    });
  }

  assign(complaint: Complaint) {
    if (!this.selectedTeam) return;
    this.api.assignToTeam(complaint.id, this.selectedTeam.id).subscribe(() => {
      this.messageService.add({ severity: 'success', summary: 'Assigned', detail: `to ${this.selectedTeam?.name}` });
    });
  }

  verify(complaint: Complaint) {
    this.api.verifyComplaint(complaint.id).subscribe(() => {
      this.messageService.add({ severity: 'success', summary: 'Verified', detail: 'Complaint closed' });
    });
  }

  reject(complaint: Complaint) {
    if (!this.selectedTeam) return;
    this.api.rejectComplaint(complaint.id, this.selectedTeam.id).subscribe(() => {
      this.messageService.add({ severity: 'warn', summary: 'Rejected', detail: 'Reassigned to new team' });
    });
  }
}