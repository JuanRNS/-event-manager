import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { SidebarComponent } from '../sidebar/sidebar';

interface Waiter {
  id: number;
  name: string;
  cpf: string;
  phone: string;
  pixKey: string;
  status: 'ativo' | 'inativo' | 'disponivel' | 'ocupado';
  createdAt: string;
}

interface Material {
  id: number;
  description: string;
  category: string;
  quantity: number;
  unitPrice: number;
  createdAt: string;
}

@Component({
  selector: 'app-event-components',
  standalone: true,
  imports: [FormsModule, CommonModule, SidebarComponent],
  templateUrl: './event-components.html',
  styleUrl: './event-components.scss'
})
export class EventComponentsComponent {
  waiterData = {
    name: '',
    phone: '',
    pixKey: '',
    status: ''
  };

  materialData = {
    description: '',
    category: 'utensilio',
    quantity: 1,
    unitPrice: 0
  };

  waiters: Waiter[] = [];

  materials: Material[] = [];

  get totalWaiters(): number {
    return this.waiters.length;
  }

  get activeWaiters(): Waiter[] {
    return this.waiters.filter(waiter => waiter.status === 'ativo' || waiter.status === 'disponivel');
  }

  get totalMaterials(): number {
    return this.materials.length;
  }

  get totalMaterialValue(): number {
    return this.materials.reduce((total, material) => total + (material.quantity * material.unitPrice), 0);
  }

  get materialsByCategory() {
    return this.materials.reduce((acc, material) => {
      if (!acc[material.category]) {
        acc[material.category] = [];
      }
      acc[material.category].push(material);
      return acc;
    }, {} as { [key: string]: Material[] });
  }

  onSubmitWaiter() {
    if (this.isWaiterFormValid()) {
      const newWaiter: Waiter = {
        id: this.getNextWaiterId(),
        name: this.waiterData.name,
        cpf: this.generateCPF(),
        phone: this.waiterData.phone,
        pixKey: this.waiterData.pixKey,
        status: this.waiterData.status as 'ativo' | 'inativo' | 'disponivel' | 'ocupado',
        createdAt: new Date().toISOString().split('T')[0]
      };

      this.waiters.push(newWaiter);
      this.resetWaiterForm();
      alert('Garçom cadastrado com sucesso!');
    } else {
      alert('Por favor, preencha todos os campos do garçom');
    }
  }

  onSubmitMaterial() {
    if (this.isMaterialFormValid()) {
      const newMaterial: Material = {
        id: this.getNextMaterialId(),
        description: this.materialData.description,
        category: this.materialData.category,
        quantity: this.materialData.quantity,
        unitPrice: this.materialData.unitPrice,
        createdAt: new Date().toISOString().split('T')[0]
      };

      this.materials.push(newMaterial);
      this.resetMaterialForm();
      alert('Material cadastrado com sucesso!');
    } else {
      alert('Por favor, preencha todos os campos do material');
    }
  }

  editWaiter(waiter: Waiter) {
    // Implementar edição do garçom
    alert(`Editando garçom: ${waiter.name}`);
  }

  deleteWaiter(id: number) {
    this.waiters = this.waiters.filter(waiter => waiter.id !== id);
  }

  deleteMaterial(id: number) {
    this.materials = this.materials.filter(material => material.id !== id);
  }

  updateWaiterStatus(id: number, status: 'ativo' | 'inativo' | 'disponivel' | 'ocupado') {
    const waiter = this.waiters.find(w => w.id === id);
    if (waiter) {
      waiter.status = status;
    }
  }

  private getNextWaiterId(): number {
    return this.waiters.length > 0 ? Math.max(...this.waiters.map(w => w.id)) + 1 : 1;
  }

  private getNextMaterialId(): number {
    return this.materials.length > 0 ? Math.max(...this.materials.map(m => m.id)) + 1 : 1;
  }

  private isWaiterFormValid(): boolean {
    return !!(
      this.waiterData.name &&
      this.waiterData.phone &&
      this.waiterData.pixKey &&
      this.waiterData.status
    );
  }

  private isMaterialFormValid(): boolean {
    return !!(
      this.materialData.description &&
      this.materialData.category &&
      this.materialData.quantity > 0 &&
      this.materialData.unitPrice >= 0
    );
  }

  private resetWaiterForm() {
    this.waiterData = {
      name: '',
      phone: '',
      pixKey: '',
      status: ''
    };
  }

  private resetMaterialForm() {
    this.materialData = {
      description: '',
      category: 'utensilio',
      quantity: 1,
      unitPrice: 0
    };
  }

  private generateCPF(): string {
    // Gerar CPF fictício para demonstração
    const numbers = Array.from({length: 9}, () => Math.floor(Math.random() * 10));
    const digit1 = this.calculateCPFDigit(numbers, [10, 9, 8, 7, 6, 5, 4, 3, 2]);
    const digit2 = this.calculateCPFDigit([...numbers, digit1], [11, 10, 9, 8, 7, 6, 5, 4, 3, 2]);
    
    return `${numbers.slice(0,3).join('')}.${numbers.slice(3,6).join('')}.${numbers.slice(6,9).join('')}-${digit1}${digit2}`;
  }

  private calculateCPFDigit(numbers: number[], weights: number[]): number {
    const sum = numbers.reduce((acc, num, index) => acc + num * weights[index], 0);
    const remainder = sum % 11;
    return remainder < 2 ? 0 : 11 - remainder;
  }
}

