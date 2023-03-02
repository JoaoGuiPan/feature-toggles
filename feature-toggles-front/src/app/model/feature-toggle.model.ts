
export class FeatureToggle {
  id?: string | null;
  technicalName: string;
  displayName: string;
  expiresOn: Date;
  description: string;
  inverted: boolean;
  customerIds: string[];

  constructor() {
      this.technicalName = '',
      this.displayName = '',
      this.expiresOn = new Date(),
      this.description = '',
      this.inverted = false,
      this.customerIds = [];
  }
}

export class FeatureToggleListItem {
  id?: string | null;
  technicalName: string;
  displayName?: string | null;

  constructor() {
    this.technicalName = '';
  }
}
