import { Component, OnInit } from '@angular/core';
import { Person } from '../person';
import { Router } from '@angular/router';
import { PersonService } from '../person.service';

@Component({
  selector: 'app-create-person',
  templateUrl: './create-person.component.html',
  styleUrls: ['./create-person.component.scss'],
})
export class CreatePersonComponent implements OnInit {
  person: Person = new Person();
  submitted = false;

  constructor(private personService: PersonService, private router: Router) {}

  ngOnInit(): void {}
  newPerson(): void {
    this.submitted = false;
    this.person = new Person();
  }

  save() {
    this.personService.createPerson(this.person).subscribe(
      (data) => console.log(data),
      (error) => console.log(error)
    );
    this.person = new Person();
    this.gotoList();
  }

  onSubmit() {
    this.submitted = true;
    this.save();
  }
  gotoList() {
    this.router.navigate(['/personal']);
  }
}
