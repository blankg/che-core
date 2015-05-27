/*******************************************************************************
 * Copyright (c) 2014-2015 Codenvy, S.A.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Codenvy, S.A. - initial API and implementation
 *******************************************************************************/

package org.eclipse.che.ide.jseditor.client.link;

/**
 * @author Evgen Vidolob
 */
public interface LinkedMode {

    void enterLinkedMode(LinkedModel model);

    void exitLinkedMode(boolean escapePosition);

    void selectLinkedGroup(int index);

}
