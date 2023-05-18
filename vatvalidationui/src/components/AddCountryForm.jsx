import { Box, Button, FormControl, TextField } from '@mui/material';
import React from 'react';
import axios from 'axios';
import { ADD_VAT_COUNTRY } from '../backendapi';

export const AddCountryForm = () => {
    const [country, setCountry] = React.useState('');
    const [countryCode, setCountryCode] = React.useState('');
    const [vatRegex, setVatRegex] = React.useState('');
    const [isSuccess, setSuccess] = React.useState(false);
    const [isError, setError] = React.useState(null);
    const [isAddAttempted, setAddAttempted] = React.useState(false);

    const handleCountryChange = (event) => {
        setCountry(event.target.value);
    };
    
    const handleCountryCodeChange = (event) => {
        setCountryCode(event.target.value);
    };

    const handleVatRegexChange = (event) => {
        setVatRegex(event.target.value);
    };


    const handleAdd = async (event) => {
        const URL_WITH_QUERY_PARAMS = ADD_VAT_COUNTRY + "?" + "country=" + country + "&countryCode=" + countryCode + "&regex=" + vatRegex;
        try {
            const response = await axios.get(URL_WITH_QUERY_PARAMS);
            setSuccess(response.data);
        } catch (error) {
            console.log(error)
            setError(error.message);
            setSuccess(false);
        }
        setAddAttempted(true);
    }
    return (
        <>
            <div>
                <FormControl sx={{ m: 1, minWidth: 120 }}>
                    <TextField id="Country-Name" label="CountryName" variant="outlined" value={country} onChange={handleCountryChange}/>
                    <div style={{ margin: '4.8px' }} />
                    <TextField id="Country-Code" label="CountryCode" variant="outlined" value={countryCode} onChange={handleCountryCodeChange}/>
                    <div style={{ margin: '4.8px' }} />
                    <TextField id="VAT-Regex" label="VAT-Regex" variant="outlined" value={vatRegex} onChange={handleVatRegexChange}/>
                    <div style={{ margin: '6.8px' }} />
                    <Button variant="outlined" onClick={handleAdd}>Add</Button>
                </FormControl>
            </div>
            <div>
                { isSuccess ? (
                    <Box sx={{ color: 'success.main' }} fontSize={30}>Successfully Created.</Box>
                ) : (
                        isError === null ? (
                            isAddAttempted ? (
                                <Box sx={{ color: 'error.main' }} fontSize={30}>Failed</Box>
                            ) : ("")
                        ) : (
                            <Box sx={{ color: 'error.main' }} fontSize={30}>{isError}</Box>   
                        )
                )}
            </div>
        </>
    );
}