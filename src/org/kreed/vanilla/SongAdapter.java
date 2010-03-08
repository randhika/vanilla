/*
 * Copyright (C) 2010 Christopher Eby <kreed@kreed.org>
 *
 * This file is part of Vanilla Music Player.
 *
 * Vanilla Music Player is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Library General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * Vanilla Music Player is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package org.kreed.vanilla;

import java.util.Arrays;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SongAdapter extends AbstractAdapter {
	private static Song[] sort(Song[] songs)
	{
		Arrays.sort(songs, new Song.TitleComparator());
		return songs;
	}

	public SongAdapter(Context context, Song[] allSongs)
	{
		super(ContextApplication.getContext(), sort(allSongs));
	}

	public View getView(int position, View convertView, ViewGroup parent)
	{
		LinearLayout view = null;
		try {
			view = (LinearLayout)convertView;
		} catch (ClassCastException e) {	
		}

		if (view == null) {
			view = new LinearLayout(mContext);
			view.setOrientation(LinearLayout.VERTICAL);
			view.setPadding(mPadding, mPadding, mPadding, mPadding);

			TextView title = new TextView(mContext);
			title.setSingleLine();
			title.setTextColor(Color.WHITE);
			title.setTextSize(mSize);
			title.setId(0);
			view.addView(title);

			TextView artist = new TextView(mContext);
			artist.setSingleLine();
			artist.setTextSize(mSize);
			artist.setId(1);
			view.addView(artist);
		}

		Song song = get(position);
		((TextView)view.findViewById(0)).setText(song.title);
		((TextView)view.findViewById(1)).setText(song.artist);
		return view;
	}
}