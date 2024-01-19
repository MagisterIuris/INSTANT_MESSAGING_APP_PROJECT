import { ComponentFixture, TestBed } from "@angular/core/testing";
import { MatDialogRef } from "@angular/material/dialog";
import { DeleteConfirmationDialogComponent } from "./delete-confirmation-dialog.component";
import { HttpClientModule } from "@angular/common/http";

class MatDialogRefStub {
  closeValue: string | undefined;

  close(value?: string): void {
    this.closeValue = value;
  }
}

describe("DeleteConfirmationDialogComponent", () => {
  let component: DeleteConfirmationDialogComponent;
  let fixture: ComponentFixture<DeleteConfirmationDialogComponent>;
  let matDialogRefStub: MatDialogRefStub;

  beforeEach(() => {
    matDialogRefStub = new MatDialogRefStub();

    TestBed.configureTestingModule({
      declarations: [DeleteConfirmationDialogComponent],
      providers: [{ provide: MatDialogRef, useValue: matDialogRefStub }],
      imports: [HttpClientModule],
    });

    fixture = TestBed.createComponent(DeleteConfirmationDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should call dialogRef.close() with "delete" when confirmDelete is called', () => {
    component.confirmDelete();
    expect(matDialogRefStub.closeValue).toBe("delete");
  });

  it("should call dialogRef.close() without any value when cancel is called", () => {
    component.cancel();
    expect(matDialogRefStub.closeValue).toBeUndefined();
  });
});
