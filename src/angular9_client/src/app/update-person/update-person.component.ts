import { Component, OnInit } from '@angular/core';
import { Person } from '../person';
import { PersonService } from '../person.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-update-person',
  templateUrl: './update-person.component.html',
  styleUrls: ['./update-person.component.scss'],
})
export class UpdatePersonComponent implements OnInit {
  id: number;
  person: Person;
  submitted = false;
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private personService: PersonService
  ) {
    this.submitted = false;
  }

  ngOnInit() {
    this.person = new Person();

    this.id = this.route.snapshot.params['id'];

    this.personService.getPersonal(this.id).subscribe(
      (data) => {
        console.log(data);
        this.person = data;
      },
      (error) => console.log(error)
    );
  }

  updatePerson() {
    this.personService.updatePerson(this.id, this.person).subscribe(
      (data) => console.log(data),
      (error) => console.log(error)
    );
    this.person = new Person();
    this.gotoList();
  }

  onSubmit() {
    this.submitted = true;
    this.updatePerson();
  }

  gotoList() {
    this.router.navigate(['/personal']);
  }
}
