/*
 * This file is part of bisq.
 *
 * bisq is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at
 * your option) any later version.
 *
 * bisq is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with bisq. If not, see <http://www.gnu.org/licenses/>.
 */

package io.bisq.gui.util.validation;

import io.bisq.common.locale.Res;
import io.bisq.core.user.Preferences;
import io.bisq.gui.util.BsqFormatter;

import javax.inject.Inject;

public final class BsqAddressValidator extends InputValidator {

    private final Preferences preferences;
    private BsqFormatter bsqFormatter;

    @Inject
    public BsqAddressValidator(Preferences preferences, BsqFormatter bsqFormatter) {
        this.preferences = preferences;
        this.bsqFormatter = bsqFormatter;
    }

    @Override
    public ValidationResult validate(String input) {

        ValidationResult result = validateIfNotEmpty(input);
        if (result.isValid)
            return validateBsqAddress(input);
        else
            return result;
    }

    private ValidationResult validateBsqAddress(String input) {
        try {
            bsqFormatter.getAddressFromBsqAddress(input);
            return new ValidationResult(true);
        } catch (Throwable e) {
            return new ValidationResult(false, Res.get("validation.bsq.invalidFormat"));
        }
    }
}
