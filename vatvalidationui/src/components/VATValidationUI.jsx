import { Button, FormControl, InputLabel, MenuItem, Select, TextField } from '@mui/material';
import React from 'react';
import axios from "axios";
import { VAT_COUNTRIES_LIST } from '../backendapi';
import { VAT_ID_VERIFICATION } from '../backendapi';
import { VATStatusComponent } from './VATStatusComponent';


function VATValidationUI() {
    const [vatCountries, setVatCountries] = React.useState();
    const [isLoading, setIsLoading] = React.useState(true);
    const [country, setCountry] = React.useState('');
    const [VAT_ID, setVAT_ID] = React.useState('');
    const [IsSubmitted, setIsSubmitted] = React.useState(false);
    const [isValid, setIsValid] = React.useState(false);
    const [isVerified, setIsVerified] = React.useState(false);

    //Loads the Countries from web service.
    React.useEffect(() => {
        const fetchData = async () => {
          try {
            const response = await axios.get(VAT_COUNTRIES_LIST);
            setVatCountries(response.data);
            setIsLoading(false);
          } catch (error) {
            console.error('Error fetching countries:', error);
            setIsLoading(false);
          }
        };
    
        fetchData();
      }, []);


      //Handles the country change and VAT Id change.
    const handleCountryChange = (event) => {
        setCountry(event.target.value);
    };

    const handleVATIDChange = (event) => {
        setVAT_ID(event.target.value);
    };

//    const handleSubmit = (event) => {
  //      event.preventDefault();
    //    setIsSubmitted(true);
    //};

    const handleSubmit = async (event) => {
        try {
            const URL_WITH_QUERY_PARAMS = VAT_ID_VERIFICATION + "?" + "country=" + country + "&vatId=" + VAT_ID;
            console.log(URL_WITH_QUERY_PARAMS);
            const response = await axios.get(URL_WITH_QUERY_PARAMS);
            setIsValid(response.data);
        } catch (error) {
            console.error('Error fetching countries:', error);
        }
        setIsVerified(true);
    }

    const renderVATVerificationComponent = () => {
        if(IsSubmitted) {
            //return <VATVerification country={country} VAT_ID={VAT_ID}/>;
        } else {
           // console.log("Not Submitted")
        }
    };

    return (
        <>
            <div>

            {
                isLoading ? ( <p>Loading countries...</p>) 
                    : ( 
                        <FormControl sx={{ m: 1, minWidth: 120 }}>
                            <InputLabel id="country-select-label">Country</InputLabel>
                            
                            <Select
                            labelId="country-select"
                            id="country-select"
                            value={country}
                            label="country"
                            onChange={handleCountryChange}
                            >
                            <MenuItem value="">
                                <em>None</em>
                            </MenuItem>

                            {vatCountries.map((country) => (
                                <MenuItem key={country} value={country}>
                                {country}
                                </MenuItem>
                            ))}
                        
                            </Select>
                            
                            <div style={{ margin: '4.8px' }} />
                            <TextField id="standard-basic" label="VATID" variant="outlined" onChange={handleVATIDChange} required/>
  
                            <div style={{ margin: '5.8px' }} />
                            <Button id='standard-buton' label="verifyvatid" variant="contained" onClick={handleSubmit}>Verify VAT ID</Button>
                            <div style={{ margin: '5.8px' }} />
                            <Button id='standard-buton' label="reset" variant="contained">Reset</Button>
                        </FormControl>
                    )
            }

            </div>
            <div><VATStatusComponent isValid={isValid} isVerified={isVerified}/></div>
        </>
    );
}

export default VATValidationUI;