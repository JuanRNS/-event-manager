import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { SidebarComponent } from '../sidebar/sidebar.component';

interface Party {
  id: number;
  location: string;
  clientName: string;
  address: string;
  materials: string;
  value: number;
  date: string;
  status: 'ativo' | 'concluido' | 'cancelado';
}

@Component({
  selector: 'app-party-registration',
  standalone: true,
  imports: [FormsModule, CommonModule, SidebarComponent],
  templateUrl: './party-registration.component.html',
  styleUrl: './party-registration.component.scss'
})
export class PartyRegistrationComponent {
  partyData = {
    location: '',
    clientName: '',
    address: '',
    materials: '',
    value: 0
  };

  parties: Party[] = [];

  // Getters para valores dinâmicos
  get totalParties(): number {
    return this.parties.length;
  }

  get totalValue(): number {
    return this.parties.reduce((total, party) => total + party.value, 0);
  }

  get activeParties(): Party[] {
    return this.parties.filter(party => party.status === 'ativo');
  }

  get completedParties(): Party[] {
    return this.parties.filter(party => party.status === 'concluido');
  }

  onSubmitParty() {
    if (this.isFormValid()) {
      const newParty: Party = {
        id: this.getNextId(),
        location: this.partyData.location,
        clientName: this.partyData.clientName,
        address: this.partyData.address,
        materials: this.partyData.materials,
        value: this.partyData.value,
        date: new Date().toISOString().split('T')[0],
        status: 'ativo'
      };

      this.parties.push(newParty);
      this.resetForm();
      alert('Festa cadastrada com sucesso!');
    } else {
      alert('Por favor, preencha todos os campos');
    }
  }

  deleteParty(id: number) {
    this.parties = this.parties.filter(party => party.id !== id);
  }

  updatePartyStatus(id: number, status: 'ativo' | 'concluido' | 'cancelado') {
    const party = this.parties.find(p => p.id === id);
    if (party) {
      party.status = status;
    }
  }

  private getNextId(): number {
    return this.parties.length > 0 ? Math.max(...this.parties.map(p => p.id)) + 1 : 1;
  }

  private isFormValid(): boolean {
    return !!(
      this.partyData.location &&
      this.partyData.clientName &&
      this.partyData.address &&
      this.partyData.materials &&
      this.partyData.value > 0
    );
  }

  private resetForm() {
    this.partyData = {
      location: '',
      clientName: '',
      address: '',
      materials: '',
      value: 0
    };
  }
}

