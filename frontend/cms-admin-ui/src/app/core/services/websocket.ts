import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { Client } from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';
import { Complaint } from './api';

@Injectable({
  providedIn: 'root'
})
export class WebSocket {
  private client: Client;
  public complaintUpdates = new Subject<Complaint>();
  public slaBreachAlerts = new Subject<void>();

  constructor() {
    this.client = new Client({
      webSocketFactory: () => new SockJS('http://localhost:8081/ws'),
      reconnectDelay: 5000
    });

    this.client.onConnect = () => {
      this.client.subscribe('/topic/complaints', message => {
        const complaint = JSON.parse(message.body) as Complaint;
        this.complaintUpdates.next(complaint);
      });

      this.client.subscribe('/topic/sla-breach', () => {
        this.slaBreachAlerts.next();
      });
    };

    this.client.activate();
  }
}