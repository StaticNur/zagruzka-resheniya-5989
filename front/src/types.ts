export interface Product {
  id: number
  name: string
  description: string
  explanationForManager: string
  category: string
  parameters: {
    id: number
  }[]
}

export interface Parameter {
  id: number; 
  name: string;
  description: string;
  typeParameter: string;
  coefficientPositive: number; 
  coefficientNegative: number;
  typeView: string; 
  minValue: number; 
  maxValue: number; 
  checking: boolean; 
}
