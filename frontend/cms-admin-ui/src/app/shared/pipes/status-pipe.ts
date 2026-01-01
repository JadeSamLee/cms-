import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'statusPriority',
  standalone: true
})
export class StatusPriorityPipe implements PipeTransform {
  private statusMap: Record<string, string> = {
    OPEN: 'Open',
    ASSIGNED_TO_TEAM: 'Assigned to Team',
    IN_PROGRESS: 'In Progress',
    RESOLVED: 'Resolved',
    CLOSED: 'Closed',
    REASSIGNED: 'Reassigned'
  };

  private priorityMap: Record<string, string> = {
    P0: 'Critical',
    P1: 'High',
    P2: 'Medium',
    P3: 'Low'
  };

  transform(value: string, type: 'status' | 'priority' = 'status'): string {
    return type === 'status' ? this.statusMap[value] || value : this.priorityMap[value] || value;
  }
}