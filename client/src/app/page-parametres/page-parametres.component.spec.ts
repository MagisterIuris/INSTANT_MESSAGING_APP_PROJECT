import { ComponentFixture, TestBed } from "@angular/core/testing";
import { MatDialogModule } from "@angular/material/dialog";
import { PageParametresComponent } from "./page-parametres.component";
import { HttpClientModule } from "@angular/common/http";
import { MatSelectModule } from "@angular/material/select";

describe("PageParametresComponent", () => {
  let component: PageParametresComponent;
  let fixture: ComponentFixture<PageParametresComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PageParametresComponent],
      imports: [MatDialogModule, HttpClientModule, MatSelectModule],
    });
    fixture = TestBed.createComponent(PageParametresComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
});
