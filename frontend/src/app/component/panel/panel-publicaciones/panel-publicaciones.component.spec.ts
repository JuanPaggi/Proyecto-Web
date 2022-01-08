import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PanelPublicacionesComponent } from './panel-publicaciones.component';

describe('PanelPublicacionesComponent', () => {
  let component: PanelPublicacionesComponent;
  let fixture: ComponentFixture<PanelPublicacionesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PanelPublicacionesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PanelPublicacionesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
