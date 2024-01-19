import { ComponentFixture, TestBed } from "@angular/core/testing";
import { HttpClientModule } from "@angular/common/http";
import { MatDialog, MatDialogModule } from "@angular/material/dialog";
import { MatIconModule } from "@angular/material/icon";
import { MatSidenavModule } from "@angular/material/sidenav";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { RouterModule } from "@angular/router";
import { FormsModule } from "@angular/forms";
import { ListeConversationComponent } from "./liste-conversation.component";

class MatDialogStub {
  open() {
    return {
      afterClosed: () => ({
        subscribe: (callback: (result: string) => void) => {
          callback(
            "Résultat simulé après la fermeture de la boîte de dialogue",
          );
        },
      }),
    };
  }
}

describe("ListeConversationComponent", () => {
  let component: ListeConversationComponent;
  let fixture: ComponentFixture<ListeConversationComponent>;

  const matDialogStub = new MatDialogStub();

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ListeConversationComponent],
      imports: [
        HttpClientModule,
        MatDialogModule,
        MatSidenavModule,
        BrowserAnimationsModule,
        MatIconModule,
        RouterModule.forRoot([]),
        FormsModule,
      ],
      providers: [{ provide: MatDialog, useValue: matDialogStub }],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListeConversationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
});
