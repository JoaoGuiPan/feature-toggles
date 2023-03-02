
export class FeatureToggle {
  id?: string;
  technicalName: string;
  displayName: string;
  expiresOn: Date;
  description: string;
  inverted: boolean;
  customerIds: string[];

  constructor() {
      this.technicalName = null,
      this.displayName = null,
      this.expiresOn = null,
      this.description = null,
      this.inverted = false,
      this.customerIds = [];
  }
}

export interface FeatureToggleListItem {
  id?: string;
  technicalName: string;
  displayName?: string;
}
