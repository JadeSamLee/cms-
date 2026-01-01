import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ComplaintDashboard } from './components/complaint-dashboard/complaint-dashboard';
import { TeamsManagement } from './components/teams-management/teams-management';
import { SlaManagement } from './components/sla-management/sla-management';

@Component({
  selector: 'app-admin',
  standalone: true,
  imports: [
    RouterOutlet,
    ComplaintDashboard,
    TeamsManagement,
    SlaManagement
  ],
  template: './admin.html',
})
export class AdminComponent { }