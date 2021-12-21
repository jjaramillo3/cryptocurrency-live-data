import { Component } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})


export class AppComponent {
  title = 'front';

  isClicked: boolean = false;

  backendResponse: any = null;

  private dataUrl : string = 'http://localhost:8080/liveData';

  private webClient : HttpClient;

  constructor(private http : HttpClient) {
    this.webClient = http;
  }

  getData() {

    this.isClicked = true;

    console.log("Button has been pressed");

    this.webClient.get<string>(this.dataUrl).subscribe(data => {
      this.backendResponse=data;
      console.log(data);
    });

  }

}

class CurrencyRateResponse {
  rates?: Rate;
  errorMessage?: string;
}

class Rate {
  usd?: CurrencyInformation;
}

class CurrencyInformation {
  name?: string;
  unit?: string;
  value?: string;
  type?: string;
}
