/*
 * Copyright (C) 2016-2018 phantombot.tv
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package tv.phantombot.event.gamewisp;

public class GameWispChangeEvent extends GameWispEvent {
    private final String username;
    private final String status;

    /*
     * Class constructor
     *
     * @param {String} username
     * @param {String} status
     */
    public GameWispChangeEvent(String username, String status) {
        this.username = username;
        this.status = status;
    }

    /*
     * Method that returns the subscriber's name.
     *
     * @return {String} username
     */
    public String getUsername() {
        return this.username;
    }

    /*
     * Method that returns the subscriber's status.
     *
     * @return {String} status
     */
    public String getStatus() {
        return this.status;
    }
}

