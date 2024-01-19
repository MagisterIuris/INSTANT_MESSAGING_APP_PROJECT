import { HttpClientTestingModule } from "@angular/common/http/testing";
import { ComponentFixture, TestBed } from "@angular/core/testing";
import { FormsModule } from "@angular/forms";
import { MatCardModule } from "@angular/material/card";
import { MatSnackBarModule } from "@angular/material/snack-bar";
import { SeConnecterComponent } from "./se-connecter.component";

describe("SeConnecterComponent", () => {
  let component: SeConnecterComponent;
  let fixture: ComponentFixture<SeConnecterComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SeConnecterComponent],
      imports: [
        HttpClientTestingModule,
        MatCardModule,
        FormsModule,
        MatSnackBarModule,
      ],
    });
    fixture = TestBed.createComponent(SeConnecterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
});
