import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ClientService } from '../client';
import { Client } from '../model/Client';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-client-edit',
  imports: [FormsModule, ReactiveFormsModule, MatFormFieldModule, MatInputModule, MatButtonModule ],
  templateUrl: './client-edit.html',
  styleUrl: './client-edit.scss',
})
export class ClientEditComponent {
  client: Client=new Client();
  errorMessage: string = '';

    constructor(
        public dialogRef: MatDialogRef<ClientEditComponent>,
          @Inject(MAT_DIALOG_DATA) public data: {Client : Client},
          private ClientService: ClientService
    ) {}

    ngOnInit(): void {
        this.client = this.data.Client ? Object.assign({}, this.data.Client) : new Client();
    }

    onSave() {
        this.errorMessage = '';
        if (!this.client.name.trim()) {
            this.errorMessage = 'El nombre no puede estar vacío.';
            return;
        }

        this.ClientService.saveClient(this.client).subscribe({
            next: () => {
                this.dialogRef.close();
            },
            error: (error) => {
                this.errorMessage = error.error ;
                console.error('Error saving client:', error);
            }
        });
    }

    onClose() {
        this.dialogRef.close();
    }
}
