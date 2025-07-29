import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';

interface DashboardWaiter {
  id: number;
  name: string;
  weeklyParties: number;
  totalEarnings: number;
}

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, RouterModule, MatIconModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss'
})
export class DashboardComponent {
  waiters: DashboardWaiter[] = [];

  // Valores dinâmicos calculados
  get totalWaiters(): number {
    return this.waiters.length;
  }

  get totalParties(): number {
    return this.waiters.reduce((total, waiter) => total + waiter.weeklyParties, 0);
  }

  get totalPaid(): number {
    return this.waiters.reduce((total, waiter) => total + waiter.totalEarnings, 0);
  }

  // Métodos para adicionar/remover garçons
  addWaiter(waiter: DashboardWaiter) {
    this.waiters.push(waiter);
  }

  removeWaiter(id: number) {
    this.waiters = this.waiters.filter(waiter => waiter.id !== id);
  }

  updateWaiter(id: number, updatedWaiter: Partial<DashboardWaiter>) {
    const index = this.waiters.findIndex(waiter => waiter.id === id);
    if (index !== -1) {
      this.waiters[index] = { ...this.waiters[index], ...updatedWaiter };
    }
  }
}

