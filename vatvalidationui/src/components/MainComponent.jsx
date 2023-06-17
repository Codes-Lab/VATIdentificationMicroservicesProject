import { FormControlLabel, FormGroup, Switch } from '@mui/material';
import React, { useState } from 'react';
import { AddCountryForm } from './AddCountryForm';
import VATValidationUI from './VATValidationUI';
import { DeleteCountryForm } from './DeleteCountryForm';
import { VAT_COUNTRIES_LIST } from '../backendapi';
import axios from 'axios';

export const MainComponent = () => {
    const [vatCountries, setVatCountries] = React.useState();
    const [isAddCountrySwitch, setAddCountrySwitch] = useState(false);
    const [isDeleteCountrySwitch, setDeleteCountrySwitch] = useState(false);

    const handleAddCountry = () => {
        setAddCountrySwitch(!isAddCountrySwitch);  
    };

    const handleDeleteCountry = async () => {
        try {
            console.log("delete countries")
            const response = await axios.get(VAT_COUNTRIES_LIST);
            setVatCountries(response.data);
        } catch (error) {
            console.error('Error fetching countries:', error);
        }
        setDeleteCountrySwitch(!isDeleteCountrySwitch);
    };

    return (
        <div>
           <FormGroup>
                <FormControlLabel control={<Switch checked={isAddCountrySwitch}/>} label="Add new Country"  disabled={isDeleteCountrySwitch} onChange={handleAddCountry}/>
                <FormControlLabel control={<Switch checked={isDeleteCountrySwitch}/>} label="Delete Country"  disabled={isAddCountrySwitch} onChange={handleDeleteCountry}/>
                {
                    isAddCountrySwitch ? (
                        <AddCountryForm handleAddCountry = {handleAddCountry}/>
                    ) : (
                        isDeleteCountrySwitch ? (
                            <DeleteCountryForm vatCountries={vatCountries}/>
                        ) : (
                            <VATValidationUI></VATValidationUI>
                        )
                    )
                }
            </FormGroup>
        </div>
    );
}