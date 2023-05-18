import { FormControlLabel, FormGroup, Switch } from '@mui/material';
import React, { useState } from 'react';
import { AddCountryForm } from './AddCountryForm';
import VATValidationUI from './VATValidationUI';
import { DeleteCountryForm } from './DeleteCountryForm';
import { VAT_COUNTRIES_LIST } from '../backendapi';
import axios from 'axios';

const label = { inputProps: { 'aria-label': 'Color switch demo' } };

export const MainComponent = () => {
    const [vatCountries, setVatCountries] = React.useState();
    const [isAddCountrySwitch, setAddCountrySwitch] = useState(false);
    const [isDeleteCountrySwitch, setDeleteCountrySwitch] = useState(false);

    const handleAddCountry = (event) => {
        setAddCountrySwitch(event.target.checked);
    };

    const handleDeleteCountry = async (event) => {
        try {
            const response = await axios.get(VAT_COUNTRIES_LIST);
            setVatCountries(response.data);
        } catch (error) {
            console.error('Error fetching countries:', error);
        }
        setDeleteCountrySwitch(event.target.checked);
    };

    return (
        <div>
           <FormGroup>
                <FormControlLabel control={<Switch />} label="Add new Country"  disabled={isDeleteCountrySwitch} onChange={handleAddCountry}/>
                <FormControlLabel control={<Switch />} label="Delete Country"  disabled={isAddCountrySwitch} onChange={handleDeleteCountry}/>
                {
                    isAddCountrySwitch ? (
                        <AddCountryForm />
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