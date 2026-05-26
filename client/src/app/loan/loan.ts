import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Pageable } from '../core/model/page/Pageable';
import { Loan } from './model/Loan';
import { LoanPage } from './model/LoanPage';
import { HttpClient } from '@angular/common/http';
import { Client } from '../client/model/Client';
import { Game } from '../game/model/Game';


@Injectable({
    providedIn: 'root',
})
export class LoanService {
    constructor(private http: HttpClient) {}

     private baseUrl = 'http://localhost:8080/loan';

    getLoans(pageable: Pageable, client?: Client, game?: Game, date?: Date): Observable<LoanPage> {
        const body: any = { pageable };

        if (client?.id) {
            body.client = { id: client.id };
        }

        if (game?.id) {
            body.game = { id: game.id };
        }

        if (date) {
            body.date = date;
        }

        return this.http.post<LoanPage>(this.baseUrl, body);
    }

    saveLoan(loan: Loan): Observable<Loan> {
        const { id } = loan;
        const url = id ? `${this.baseUrl}/${id}` : this.baseUrl;
        return this.http.put<Loan>(url, loan);
    }

    deleteLoan(idLoan: number): Observable<void> {
        return this.http.delete<void>(`${this.baseUrl}/${idLoan}`);
    }

    getAllLoans(): Observable<Loan[]> {
        return this.http.get<Loan[]>(this.baseUrl);
    }
}
