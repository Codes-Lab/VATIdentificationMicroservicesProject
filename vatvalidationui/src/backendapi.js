const {REACT_APP_VAT_BASE_URL} = process.env;

export const VAT_COUNTRIES_LIST = REACT_APP_VAT_BASE_URL + "customers/getAllCountries";
export const VAT_ID_VERIFICATION = REACT_APP_VAT_BASE_URL + "customers/verify";
export const ADD_VAT_COUNTRY = REACT_APP_VAT_BASE_URL + "customers/addcountry";
export const DELETE_VAT_COUNTRY = REACT_APP_VAT_BASE_URL + "customers/delete";