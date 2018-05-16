import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TableauGradeComponent } from './tableau-grade.component';

describe('TableauGradeComponent', () => {
  let component: TableauGradeComponent;
  let fixture: ComponentFixture<TableauGradeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TableauGradeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TableauGradeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
