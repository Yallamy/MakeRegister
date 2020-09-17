import { Injectable } from '@angular/core';
import { Observable, of, throwError } from 'rxjs';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { catchError, tap, map } from 'rxjs/operators';
import { Pessoa } from './classes/pessoa';
import { PagePessoa } from './classes/pagePessoa';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};
const apiUrl = "http://localhost:8080/api/v1/register/pessoa";

@Injectable({
  providedIn: 'root'
})
export class ApiService {

	constructor(private http: HttpClient) { }
  
	private handleError<T> (operation = 'operation', result?: T) {
	  return (error: any): Observable<T> => {

		console.error(error); // log para console 

		// manter o APP funcionando retornando um resultado vazio.
		return of(result as T);
	  };
	}

	//POST
	addPessoa (pessoa): Observable<Pessoa> {
	  return this.http.post<Pessoa>(apiUrl, pessoa, httpOptions).pipe(
		tap((pessoa: Pessoa) => console.log(`added pessoa w/ id=${pessoa.id}`)),
		catchError(this.handleError<Pessoa>('addPessoa'))
	  );
	}

	//PUT
	updatePessoa (id, pessoa): Observable<any> {
	  const url = `${apiUrl}/${id}`;
	  return this.http.put(url, pessoa, httpOptions).pipe(
		tap(_ => console.log(`updated pessoa id=${id}`)),
		catchError(this.handleError<any>('updatePessoa'))
	  );
	}

	//GET
	getPessoa(id: number): Observable<Pessoa> {
	  const url = `${apiUrl}/${id}`;
	  return this.http.get<Pessoa>(url).pipe(
		tap(_ => console.log(`fetched pessoa id=${id}`)),
		catchError(this.handleError<Pessoa>(`getPessoa id=${id}`))
	  );
	}

	//Delete
	deletePessoa (id): Observable<Pessoa> {
	  const url = `${apiUrl}/${id}`;

	  return this.http.delete<Pessoa>(url, httpOptions).pipe(
		tap(_ => console.log(`deleted pessoa id=${id}`)),
		catchError(this.handleError<Pessoa>('deletePessoa'))
	  );
	}

	//GET ALL
	getPessoas (): Observable<any> {
	  return this.http.get<Pessoa>(apiUrl, httpOptions)
		.pipe(
		  map(res => {  
			  return {
					content: res['content'],
					totalPages: res['totalPages']
			  };
		  }),
		  tap(pessoas => console.log('fetched pessoas')),
		  catchError(this.handleError('getPessoas', []))
		);
	}

}

