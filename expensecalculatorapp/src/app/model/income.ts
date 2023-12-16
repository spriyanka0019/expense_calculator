export interface Income {
    category?: any;
    date?: any;  // This should be a Date object but should send in a string so as to save in the timestamp format.
    amount?: any;
}