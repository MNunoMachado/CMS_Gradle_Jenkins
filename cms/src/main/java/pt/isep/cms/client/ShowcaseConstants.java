/*
 * Copyright 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package pt.isep.cms.client;

import pt.isep.cms.client.MainMenuTreeViewModel.MenuConstants;

import pt.isep.cms.contacts.client.CwContacts;
import pt.isep.cms.contacts.client.view.ContactsDialog;
import pt.isep.cms.contacts.client.ContactsController;
import pt.isep.cms.warehouses.client.CwWarehouse;
import pt.isep.cms.warehouses.client.WarehousesController;
import pt.isep.cms.warehouses.client.view.WarehousesDialog;


/**
 * Constants used throughout the showcase.
 */
public interface ShowcaseConstants extends MenuConstants,
        CwContacts.CwConstants, ContactsDialog.CwConstants,
        ContactsController.CwConstants, CwWarehouse.CwConstants,
        WarehousesDialog.CwConstants, WarehousesController.CwConstants {

    /**
     * The path to source code for examples, raw files, and style definitions.
     */
    //String DST_SOURCE = "gwtShowcaseSource/";

    /**
     * The destination folder for parsed source code from Showcase examples.
     */
    //String DST_SOURCE_EXAMPLE = DST_SOURCE + "java/";

    /**
     * The destination folder for raw files that are included in entirety.
     */
    //String DST_SOURCE_RAW = DST_SOURCE + "raw/";

    /**
     * The destination folder for parsed CSS styles used in Showcase examples.
     */
    //String DST_SOURCE_STYLE = DST_SOURCE + "css/";
}
