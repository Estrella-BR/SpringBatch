import { HeaderComponent } from './header/header';
import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { DATE_PIPE_DEFAULT_OPTIONS } from '@angular/common';


@Component({
  selector: 'app-root',
  imports: [RouterOutlet, HeaderComponent],
  providers: [
    { provide: DATE_PIPE_DEFAULT_OPTIONS, useValue: { timezone: '+0200' } }
  ],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {
  protected readonly title = signal('Tutorial angular');

}
