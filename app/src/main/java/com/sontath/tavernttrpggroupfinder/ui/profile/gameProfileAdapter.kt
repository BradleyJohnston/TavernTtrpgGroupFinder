package com.sontath.tavernttrpggroupfinder.ui.profile

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sontath.tavernttrpggroupfinder.R

class gameProfileAdapter(context: Context, list: ArrayList<gameProfileUiState>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val context: Context = context
    var list: ArrayList<gameProfileUiState> = list

    public inner class gameProfileViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
            private val descriptionString: String = itemView.context.getString(R.string.game_description)
            private val partySizeString: String = itemView.context.getString(R.string.game_party_size)
            private val sessionFrequencyString: String = itemView.context.getString(R.string.game_session_frequency)
            private val gameVersionString: String = itemView.context.getString(R.string.game_version)

            var description: TextView = itemView.findViewById(R.id.textViewGameDescription)
            var partySize: TextView = itemView.findViewById(R.id.textViewGamePartySize)
            var sessionFrequency: TextView = itemView.findViewById(R.id.textViewGameSessionFrequency)
            var gameVersion: TextView = itemView.findViewById(R.id.textViewGameVersion)

            fun bind(position: Int) {
                val currentGameProfile: gameProfileUiState = list[position]

                description.text = descriptionString + currentGameProfile.settingDescription
                partySize.text = partySizeString + currentGameProfile.desiredPartySize
                sessionFrequency.text = sessionFrequencyString + currentGameProfile.desiredSessionFrequency
                gameVersion.text = gameVersionString + currentGameProfile.gameVersion

            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return gameProfileViewHolder(
            LayoutInflater.from(context).inflate(R.layout.game_profile_view, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as gameProfileViewHolder).bind(position)
    }
}