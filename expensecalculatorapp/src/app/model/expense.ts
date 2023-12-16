export interface Expense {
    date?: any;  // This should be a Date object but should send in a string so as to save in the timestamp format.
    category?: any;
    amount?: any;
}