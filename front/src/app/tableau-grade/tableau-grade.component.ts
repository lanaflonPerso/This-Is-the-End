import { Agents } from './../modeles/agents';
import { AgentService } from './../agent.service';
import { MessageService } from 'primeng/components/common/messageservice';
import { GradeService } from './../grade.service';
import { Message, SortEvent } from 'primeng/api';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Grades } from './../modeles/grade';
import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { ConfirmationService } from 'primeng/api';
import { HttpErrorResponse } from '@angular/common/http';


@Component({
  selector: 'app-tableau-grade',
  templateUrl: './tableau-grade.component.html',
  styleUrls: ['./tableau-grade.component.css'],
  encapsulation: ViewEncapsulation.None,
})
export class TableauGradeComponent implements OnInit {

  displayDialog: boolean;
  grades: Grades[];
  grade: Grades = {};
  selectedGrade: Grades;
  newGrade: boolean;
  cols: any[];
  userform: FormGroup;
  submitted: boolean;
  msgs: Message[] = [];

  constructor(private gradeService: GradeService,
    private fb: FormBuilder,
    private messageService: MessageService,
    private confirmationService: ConfirmationService) { }

  ngOnInit() {
    this.getGrades();

    this.cols = [
      { field: 'classe', header: 'Classe' },
      { field: 'grade', header: 'Grade' }

    ];
  }

  getGrades() {
    this.gradeService.getGrades().subscribe(grades => {
      this.grades = grades;
    },
      (err: HttpErrorResponse) => {
        if (err.error instanceof Error) {
          // A client-side or network error occurred. Handle it accordingly.
          console.log('une erreur c\'est produite:', err.error.message);
        } else {
          // The backend returned an unsuccessful response code.
          // The response body may contain clues as to what went wrong,
          console.log(`le back a retourné l'erreur ${err.status},
           body ${err.error.message}, bbbb, ${err.error.type}, was: ${err.error.name}`);
        }
      });
  }

  showDialogToAdd() {
    this.newGrade = true;
    this.grade = {};
    this.displayDialog = true;
  }

  save() {
    const grade = [...this.grades, this.newGrade];
    if (this.newGrade) {
      grade.push(this.grade),
        this.gradeService.createGrade(this.grade).subscribe(
          data => this.grade = data,

        );
      this.getGrades();
    } else {
      this.gradeService.updateGrade(this.grade).subscribe(() => {
      });
      this.getGrades();
    }

    this.getGrades();
    this.grade = null;
    this.displayDialog = false;
  }

  delete() {
    const index = this.grades.indexOf(this.selectedGrade);
    this.grades = this.grades.filter((val, i) => i !== index);
    this.gradeService.deleteGrade(this.grade.id).subscribe();
    this.grade = null;
    this.show();
    this.displayDialog = false;
  }

  onRowSelect(event) {
    this.newGrade = false;
    this.grade = this.cloneGrade(event.data);
    this.displayDialog = true;
  }

  cloneGrade(f: Grades): Grades {
    const grade = {};
    // tslint:disable-next-line:forin
    for (const prop in f) {
      grade[prop] = f[prop];
    }
    return grade;
  }

  // Filtre
  customSort(event: SortEvent) {
    event.data.sort((data1, data2) => {
      const value1 = data1[event.field];
      const value2 = data2[event.field];
      let result = null;

      if (value1 == null && value2 != null) {
        result = -1;
      } else if (value1 != null && value2 == null) {
        result = 1;
      } else if (value1 == null && value2 == null) {
        result = 0;
      } else if (typeof value1 === 'string' && typeof value2 === 'string') {
        result = value1.localeCompare(value2);
      } else {
        result = (value1 < value2) ? -1 : (value1 > value2) ? 1 : 0;
      }
      return (event.order * result);
    });
  }

  show() {
    this.msgs.push({ severity: 'info', summary: 'Info Message', detail: 'Grade Supprimé' });
  }

  clear() {
    this.messageService.clear();
  }

  confirm() {
    this.confirmationService.confirm({
      message: 'Etes-vous sur de vouloir supprimer cette entrée?',
      accept: () => {
        this.delete();

      }
    });
  }
}
