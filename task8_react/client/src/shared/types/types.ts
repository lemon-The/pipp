export interface Warship {
  name: string;
  shipClass: string;
  commissionDate: string;
  decommissionDate: string;
  country: Country;
}

export interface Country {
  name: string;
}
