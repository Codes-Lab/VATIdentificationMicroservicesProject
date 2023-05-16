import { Box, Typography } from '@mui/material';
import React from 'react';

export const VATStatusComponent = ({isValid, isVerified}) => {
    if(isVerified) {
        if(isValid) {
            return ( 
                <Typography component="div" variant="body1">
                    <Box sx={{ color: 'success.main' }} fontSize={30}>Success</Box>
                </Typography>
              );
        } else {
            return ( 
            <Typography component="div" variant="body1">
                <Box sx={{ color: 'error.main' }} fontSize={30}>Invalid</Box>
            </Typography>
        );
        }
    }
}; 