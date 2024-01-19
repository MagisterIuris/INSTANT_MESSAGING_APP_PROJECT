import { ComponentFixture, TestBed } from "@angular/core/testing";
import { HttpClientTestingModule } from "@angular/common/http/testing";
import { MatDialogModule, MatDialogRef } from "@angular/material/dialog";
import { MyDialogComponent } from "./my-dialog.component";
import { FormsModule } from "@angular/forms";

describe("MyDialogComponent", () => {
  let component: MyDialogComponent;
  let fixture: ComponentFixture<MyDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [MyDialogComponent],
      imports: [HttpClientTestingModule, MatDialogModule, FormsModule],
      providers: [
        {
          provide: MatDialogRef,
          useValue: {},
        },
      ],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MyDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
});
