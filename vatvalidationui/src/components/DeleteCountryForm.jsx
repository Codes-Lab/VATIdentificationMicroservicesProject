import { Box, Button, FormControl, InputLabel, MenuItem, Select } from '@mui/material';
import React from 'react';
import axios from 'axios';
import { DELETE_VAT_COUNTRY } from '../backendapi';

export const DeleteCountryForm = ({vatCountries}) => {

    const [country, setCountry] = React.useState('');
    const [isSuccess, setSuccess] = React.useState(false);
    const [isError, setError] = React.useState(null);
    const [isAddAttempted, setAddAttempted] = React.useState(false);

    const handleCountryChange = (event) => {
        setCountry(event.target.value);
    };

    const handleDelete = async (event) => {
        const URL_WITH_QUERY_PARAMS = DELETE_VAT_COUNTRY + "?" + "country=" + country;
        try {
            const response = await axios.get(URL_WITH_QUERY_PARAMS);
            setSuccess(response.data);
        } catch (error) {
            setError(error.message);
            setSuccess(false);
        }
        setAddAttempted(true);
    }

    return (
        <>
            <div>
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

                    <div style={{ margin: '6.8px' }} />
                    <Button variant="outlined" onClick={handleDelete}>Delete</Button>
                </FormControl>
            </div>
            <div>
                { isSuccess ? (
                    <Box sx={{ color: 'success.main' }} fontSize={30}>Successfully Deleted.</Box>
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